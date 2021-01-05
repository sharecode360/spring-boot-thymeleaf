package com.example.springboot.controller.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class StudentForm implements Serializable  {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "{stuName.notnull.message}")
    private String stuName;

    private String sex;

    @NotNull(message = "{age.notnull.message}")
    @Max(value=100, message = "{age.range.message}")
    @Min(value=1, message = "{age.range.message}")
    private Integer age;
}
