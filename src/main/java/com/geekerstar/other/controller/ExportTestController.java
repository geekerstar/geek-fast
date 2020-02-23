package com.geekerstar.other.controller;

import com.geekerstar.other.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2020/2/19 09:26
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/exportTest")
public class ExportTestController {

    @Autowired
    private ExportService exportService;


}
