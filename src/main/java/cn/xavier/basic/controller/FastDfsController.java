package cn.xavier.basic.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.FastDfsUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zheng-Wei Shui
 * @date 11/20/2021
 */
@RestController
@RequestMapping("/fastDfs")
public class FastDfsController {

    @PostMapping("/upload")
    @SneakyThrows
    // the parameter required  Defaults to true
    public AjaxResponse upload(@RequestPart MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 文件扩展名
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        // fastDfs返回文件路径
        String pathInDfs = FastDfsUtil.upload(file.getBytes(), extension);
        return AjaxResponse.of().setResultObj(pathInDfs);
    }

    // DELETE http://localhost:8080/fastDfs?path=/group1/M00/01/A9/rBE3kWGZIKuAdTZlAAQnPJ-EZ0s586.jpg
    // @RequestParam 可省略，同名参数接收
    @DeleteMapping
    public AjaxResponse delete(String path) {
        String pathTemp = path.substring(1); // group1/M00/01/A9/rBE3kWGZIKuAdTZlAAQnPJ-EZ0s586.jpg
        int indexOfFirstSlash = pathTemp.indexOf("/");
        String groupName = pathTemp.substring(0, indexOfFirstSlash);
        String fileName = pathTemp.substring(indexOfFirstSlash + 1);
        FastDfsUtil.delete(groupName, fileName);
        return AjaxResponse.of();
    }
}
