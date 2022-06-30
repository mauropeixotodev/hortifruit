package com.fpsoluctionstechs.hortfruitonline.controller.file;

import com.fpsoluctionstechs.hortfruitonline.controller.file.response.FileResponse;
import com.fpsoluctionstechs.hortfruitonline.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @PostMapping("")
    public ResponseEntity<FileResponse> upload(@RequestParam("file") MultipartFile file) {
        try {
            String nome = filesService.save(file);
            return ResponseEntity.status(HttpStatus.OK).body(FileResponse.builder().nome(nome).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(FileResponse.builder().nome("").build());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<FileResponse>> list() {
        List<FileResponse> fileResponses = filesService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "get", path.getFileName().toString()).build().toString();
            return FileResponse.builder().nome(filename).build();
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileResponses);
    }

    @ResponseBody
    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> get(@PathVariable String filename) {
        Resource file = filesService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
