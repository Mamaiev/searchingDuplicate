package com.semitop7.controller.shell;

import com.semitop7.service.FileService;
import com.semitop7.service.file.generator.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private Generator generator;

    @ShellMethod("Generate files")
    public void generateFiles(@ShellOption(value = {"-FN", "--file_name"}, defaultValue = "") String fileName,
                              @ShellOption(value = {"-FC", "--file_count"}, defaultValue = "0") Integer fileCount,
                              @ShellOption(value = {"-FS", "--file_size"}, defaultValue = "1024") Integer fileSize,
                              @ShellOption(value = {"-SL", "--string_length"}, defaultValue = "8") Integer stringLength)
            throws IOException {
        generator.generateFiles(fileName, fileCount, fileSize, stringLength);
    }

    @ShellMethod("Read files")
    public void readFiles(@ShellOption(value = {"-FN", "--file_name"}, defaultValue = "") String fileName,
                          @ShellOption(value = {"-FC", "--file_count"}, defaultValue = "0") Integer fileCount)
            throws IOException {
        fileService.readFiles(fileName, fileCount);
    }
}
