package com.xxx.project.controller;

import com.xxx.project.common.MsgException;
import com.xxx.project.entity.Counselor;
import com.xxx.project.entity.PlanFile;
import com.xxx.project.entity.Student;
import com.xxx.project.repository.PlanFileRepository;
import com.xxx.project.service.CounselorService;
import com.xxx.project.service.StudentService;
import javassist.bytecode.ByteArray;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private PlanFileRepository planFileRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CounselorService counselorService;

    public static String getCellValue(Cell cell) {
        if (cell == null) return "xxx";
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            Double d = cell.getNumericCellValue();
            return String.valueOf(d);
        }
        return String.valueOf(cell.getStringCellValue());
    }
    // 学生信息excel文档解析
    @PostMapping("/upload/student")
    @Transactional
    public String uploadExcel (MultipartFile file) throws IOException, MsgException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int total = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < total; i++) {
            Row row = sheet.getRow(i);
            Student student = new Student();
            System.err.println(getCellValue(row.getCell(0)));
            System.err.println(row.getCell(0).getCellType());
            student.setStudentId(getCellValue(row.getCell(0)));
            student.setName(getCellValue(row.getCell(1)));
            if (getCellValue(row.getCell(2)).equals("男")) {
                student.setGender(true);
            } else {
                student.setGender(false);
            }
            student.setCollege(getCellValue(row.getCell(3)));
            student.setProfession(getCellValue(row.getCell(4)));
            student.setPhoneNumber(getCellValue(row.getCell(5)));
            student.setCode(getCellValue(row.getCell(6)));
            student.setClassNum(getCellValue(row.getCell(7)));
            student.setGradeNum(getCellValue(row.getCell(8)));
            studentService.add(student);
        }
        return "上传成功!";
    }  // 教师信息excel文档解析
    @PostMapping("/upload/teacher")
    @Transactional
    public String uploadExcel1 (MultipartFile file) throws IOException, MsgException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int total = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < total; i++) {
            Row row = sheet.getRow(i);
            Counselor counselor = new Counselor();
            counselor.setCounselorId(getCellValue(row.getCell(0)));
            counselor.setName(getCellValue(row.getCell(1)));
            if (getCellValue(row.getCell(2)).equals("男")) {
               counselor.setGender(true);
            } else {
                counselor.setGender(false);
            }
            counselor.setCollege(getCellValue(row.getCell(3)));
            counselor.setPhoneNumber(getCellValue(row.getCell(4)));
            counselor.setCode(getCellValue(row.getCell(5)));
          counselorService.add(counselor);
        }
        return "上传成功!";
    }
    // 文件上传
    @PostMapping(value = "/upload/{id}")
    public PlanFile upload(@PathVariable(name = "id") String extraId, @RequestParam(name = "name") String name, @RequestParam(name = "type") String type, @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        PlanFile planFile = new PlanFile();
        planFile.setFile(file.getBytes());
        planFile.setExtraId(extraId);
        planFile.setName(name);
        planFile.setType(type);
        planFileRepository.save(planFile);
        planFile.setFile(null);
        return planFile;
    }

    // 获取文件列表
    @GetMapping(value = "/{id}")
    public List<PlanFile> getFile(@PathVariable(value = "id") String id) {
        List<PlanFile> list = planFileRepository.findAllByExtraId(id);
        List<PlanFile> ret = list.stream().filter(obj -> {
            obj.setFile(null);
            return true;
        }).collect(Collectors.toList());
        return ret;
    }

    // 删除单个文件
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        planFileRepository.deleteById(id);
    }

    // 下载文件
    @GetMapping(value = "/download/{id}")
    public String onePicture(@PathVariable(value = "id") String fileId, HttpServletResponse response) throws UnsupportedEncodingException {
        PlanFile planFile = planFileRepository.getOne(fileId);
        if (planFile.getFile() != null) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(planFile.getName(), "UTF-8"));// 设置文件名
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                os.write(planFile.getFile(), 0, planFile.getFile().length);
                return "下载成功";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "下载失败";
    }

    // 下载文件模板
    @GetMapping(value = "/download/student")
    public void getStudentTemplate(HttpServletResponse response) throws UnsupportedEncodingException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("学生文件模板");
        Row row = sheet.createRow(0);
        String[] strings = new String[]{"学号", "姓名", "性别", "学院", "专业", "电话", "身份证号码", "班级", "年级"};
        for (int i = 0; i < 9; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(strings[i]);
        }
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("学生信息导入.xlsx", "UTF-8"));// 设置文件名
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 下载文件模板
    @GetMapping(value = "/download/teacher")
    public void getTeacherTemplate(HttpServletResponse response) throws UnsupportedEncodingException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("教师文件模板");
        Row row = sheet.createRow(0);
        String[] strings = new String[]{"教师号", "姓名", "性别", "学院", "电话", "身份证号码"};
        for (int i = 0; i < 6; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(strings[i]);
        }
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("教师文件模板.xlsx", "UTF-8"));// 设置文件名
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
