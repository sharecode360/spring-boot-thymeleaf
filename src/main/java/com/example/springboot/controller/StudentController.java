package com.example.springboot.controller;

import com.example.springboot.controller.forms.StudentForm;
import com.example.springboot.domain.model.Student;
import com.example.springboot.domain.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final String STUDENT_LIST = "studentList";
    private final String STUDENT_DETAIL = "studentDetail";

    @Autowired
    private StudentService studentService;

    /**
     * 指定された学生のIDによって学生情報を返す
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String getStudent(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.getStudentById(id);
        StudentForm studentForm = new StudentForm();
        BeanUtils.copyProperties(student, studentForm);
        model.addAttribute("studentForm", studentForm);
        return STUDENT_DETAIL;
    }

    @GetMapping("/new")
    public String getStudent(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return STUDENT_DETAIL;
    }

    /**
     * 条件を満たした学生情報を取得する
     * @param studentForm
     * @return
     */
    @GetMapping
    public String getStudents(StudentForm studentForm, Model model) {
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        model.addAttribute("studentForms", studentService.getStudents(student));
        return STUDENT_LIST;
    }

    /***
     * 学生情報を追加する
     * @param studentForm
     * @return
     */
    @PostMapping
    public String addStudent(@Validated StudentForm studentForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return STUDENT_DETAIL;
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        studentService.addStudent(student);
        return "redirect:/student";
    }

    /**
     * 学生情報を更新する
     * @param studentForm
     * @return
     */
    @PutMapping
    public String updateStudent(@Validated StudentForm studentForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return STUDENT_DETAIL;
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentForm, student);
        studentService.updateStudent(student);
        return "redirect:/student";
    }

    /**
     * 指定した学生のIDによって学生情報を削除する
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable("id") Integer id) {
        studentService.deleteStudentById(id);
        return "redirect:/student";
    }
}