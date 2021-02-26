package com.example.demo.Admin;

import com.example.demo.Hibernate.HangSxEntity;
import com.example.demo.Hibernate.LoaiSpEntity;
import com.example.demo.Hibernate.SanphamEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.*;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//@ModelAttribute dùng để lấy tham số trả về 1 đối tượng, @RequestParam dùng để lấy tham số trả về int,string,long,...

@Controller
public class SanPhamController {

    String local = "localhost:8080";

    @GetMapping("/admin/sanpham/page/{pageNumber}")
    public String SanPhamPaginated(@PathVariable int pageNumber, Model model,HttpSession httpSession,HttpServletRequest httpServletRequest) throws IOException {
        if (httpSession.getAttribute("username") != null) {
            List<SanphamEntity> sanPhams = new ArrayList<>();
            List<LoaiSpEntity> loaiSPS = new ArrayList<>();
            List<HangSxEntity> hangSXES = new ArrayList<>();

            //call api sanphams
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://"+local+"/api/sanphams/list").build();

            Response response = okHttpClient.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                sanPhams = Arrays.asList(objectMapper.readValue(response.body().string(), SanphamEntity[].class));
            } catch (IOException e) {
            }
            //===========

            //call api loaisps
            OkHttpClient okHttpClientLoaiSP = new OkHttpClient();;
            Request requestLoaiSP = new Request.Builder().url("http://"+local+"/api/loaisps/list").build();

            Response responseLoaiSP = okHttpClientLoaiSP.newCall(requestLoaiSP).execute();
            try {
                loaiSPS = Arrays.asList(objectMapper.readValue(responseLoaiSP.body().string(),LoaiSpEntity[].class));
            }catch (IOException e){

            }
            //================

            //call api hangsxs
            OkHttpClient okHttpClientHangSX = new OkHttpClient();
            Request requestHangSX = new Request.Builder().url("http://"+local+"/api/hangsxs/list").build();

            Response responseHangSX = okHttpClientHangSX.newCall(requestHangSX).execute();
            try {
                hangSXES = Arrays.asList(objectMapper.readValue(responseHangSX.body().string(),HangSxEntity[].class));
            }catch (IOException e){

            }
            //================

            PagedListHolder<SanphamEntity> pagedListHolder = (PagedListHolder<SanphamEntity>) httpServletRequest.getSession().getAttribute("sanphams");
            int pageSize=10;
            if(pagedListHolder == null){
                pagedListHolder = new PagedListHolder<>(sanPhams);
                pagedListHolder.setPageSize(pageSize);
            }else {
                pagedListHolder = new PagedListHolder<>(sanPhams);
                pagedListHolder.setPageSize(pageSize);
                final int goToPage = pageNumber-1;
                if(goToPage <= pagedListHolder.getPageCount() && goToPage >=0){
                    pagedListHolder.setPage(goToPage);
                }
            }

            //Paginated
            httpServletRequest.getSession().setAttribute("sanphams",pagedListHolder);
            int current = pagedListHolder.getPage() +1;
            int begin = 1;
            int end = pagedListHolder.getPageCount();
            int totalPageCount = pagedListHolder.getPageCount();
            String baseUrl = "/admin/sanpham/page/";

            model.addAttribute("beginIndex",begin);
            model.addAttribute("endIndex",end);
            model.addAttribute("currentIndex",current);
            model.addAttribute("totalPageCount",totalPageCount);
            model.addAttribute("baseUrl",baseUrl);
            model.addAttribute("sanphamsPage",pagedListHolder);
            //===========


            model.addAttribute("HangSXs",hangSXES);
            model.addAttribute("LoaiSPs",loaiSPS);
            model.addAttribute("sanPhams", sanPhams);
            return "/admin/all-product";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/admin/all-product")
    public String allSanPham(Model model, HttpSession httpSession,HttpServletRequest request) throws IOException {
        if (httpSession.getAttribute("username") != null) {
            request.getSession().setAttribute("sanphams",null);
            return "redirect:/admin/sanpham/page/1";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/admin/addproduct")
    public String ThemMoiSanPham(@ModelAttribute SanphamEntity sanPham, RedirectAttributes redirectAttributes, @RequestParam(name = "fileImage") MultipartFile multipartFile) throws IOException {
        String nameImage = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        sanPham.setAnh(nameImage);

        //Xử lý lưu ảnh vào folder
        String upLoadDir = "src/main/resources/static/image/" + sanPham.getMaSp();
        Path uploadPath = Paths.get(upLoadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(nameImage);
            //System.out.println(filePath.toFile().getAbsoluteFile());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        }
        //=====================


        //Xử lý các thông tin khác của sản phẩm mới
        sanPham.setIsnew(1);
        sanPham.setIshot(0);
        String thoiDiemNhapHang;
        sanPham.setThoiDiemNhapHang(Timestamp.valueOf(LocalDateTime.now()));
        //=========================================

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //chuyển Object sang JSON để POST JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(sanPham));
        //=========================================
        Request request = new Request.Builder().url("http://"+local+"/api/sanphams/post").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        String stringResponse = response.body().string();
        if (stringResponse.equals("successful")) {
            redirectAttributes.addFlashAttribute("response", "Thêm mới sản phẩm thành công.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttributes.addFlashAttribute("response", stringResponse);
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/admin/sanpham/page/1";
    }

    @PostMapping("/admin/editproduct")
    public String SuaSanPham(@ModelAttribute SanphamEntity sanPham,@RequestParam(name = "nameImage") String nameImage, RedirectAttributes redirectAttributes, @RequestParam(name = "fileImage") MultipartFile multipartFile) throws IOException {

        //Lấy thông tin sản phẩm muốn sửa
        SanphamEntity sp = GetSanpham(sanPham.getMaSp());

        if(!nameImage.equals(sp.getAnh())){
            if(nameImage.equals("empty")){
                sanPham.setAnh("");
            }else {
                //Lưu ảnh
                String nImage = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                sanPham.setAnh(nImage);

                String upLoadDir = "src/main/resources/static/image/" + sp.getMaSp();
                Path uploadPath = Paths.get(upLoadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try {
                    InputStream inputStream = multipartFile.getInputStream();
                    Path filePath = uploadPath.resolve(nImage);
                    //System.out.println(filePath.toFile().getAbsoluteFile());
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                }
            }
            //=======================
        }else {
            sanPham.setAnh(sp.getAnh());
        }
        sanPham.setIsnew(sp.getIsnew());
        sanPham.setIshot(sp.getIshot());
        sanPham.setThoiDiemNhapHang(sp.getThoiDiemNhapHang());


        String result = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(sanPham));
        Request request = new Request.Builder().url("http://"+local+"/api/sanphams/put").put(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        String stringResponse = response.body().string();
        if (stringResponse.equals("successful")) {
            redirectAttributes.addFlashAttribute("response", "Đã lưu.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttributes.addFlashAttribute("response", stringResponse);
            redirectAttributes.addFlashAttribute("alertClass", "alert-alert");
        }

        return "redirect:/admin/sanpham/page/1";
    }

    @GetMapping("/admin/xoasanpham")
    public String XoaSanPham(@RequestParam(name = "MaSp") int MaSp, RedirectAttributes redirectAttributes) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://"+local+"/api/sanphams/delete/" + MaSp).delete().build();

        Response response = okHttpClient.newCall(request).execute();
        String stringResponse = response.body().string();
        if (stringResponse.equals("successful")) {
            redirectAttributes.addFlashAttribute("response", "Xoá sản phẩm thành công.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttributes.addFlashAttribute("response", stringResponse);
            redirectAttributes.addFlashAttribute("alertClass", "alert-alert");
        }

        return "redirect:/admin/sanpham/page/1";
    }

    private SanphamEntity GetSanpham(int MaSp) throws IOException {
        SanphamEntity sanPham = new SanphamEntity();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://"+local+"/api/sanphams/single/" + MaSp).build();
        Response response = okHttpClient.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        sanPham = objectMapper.readValue(response.body().string(), SanphamEntity.class);
        return sanPham;
    }

    @GetMapping("/admin/suasanpham")
    public String SuaSanPham(@RequestParam(name = "MaSp") int MaSp, Model model) {
        List<LoaiSpEntity> loaiSPS = new ArrayList<>();
        List<HangSxEntity> hangSXES = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //call api loaisps
            OkHttpClient okHttpClientLoaiSP = new OkHttpClient();
            Request requestLoaiSP = new Request.Builder().url("http://"+local+"/api/loaisps/list").build();

            Response responseLoaiSP = okHttpClientLoaiSP.newCall(requestLoaiSP).execute();
            try {
                loaiSPS = Arrays.asList(objectMapper.readValue(responseLoaiSP.body().string(), LoaiSpEntity[].class));
            } catch (IOException e) {

            }
            //================

            //call api hangsxs
            OkHttpClient okHttpClientHangSX = new OkHttpClient();
            Request requestHangSX = new Request.Builder().url("http://"+local+"/api/hangsxs/list").build();

            Response responseHangSX = okHttpClientHangSX.newCall(requestHangSX).execute();
            try {
                hangSXES = Arrays.asList(objectMapper.readValue(responseHangSX.body().string(), HangSxEntity[].class));
            } catch (IOException e) {

            }
            //================

            model.addAttribute("HangSXs", hangSXES);
            model.addAttribute("LoaiSPs", loaiSPS);
            model.addAttribute("SanPham", GetSanpham(MaSp));
        } catch (IOException e) {
            return "404 Page Not Found";
        }
        return "admin/suaSanPham";
    }

    @PostMapping("/admin/addloaisp")
    public String ThemMoiLoaiSP(@ModelAttribute LoaiSpEntity loaiSP, RedirectAttributes RedirectAttributes) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //chuyển Object sang JSON để POST đến API
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(loaiSP));
        //=========================================
        Request request = new Request.Builder().url("http://"+local+"/api/loaisps/post").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        String stringResponse = response.body().string();
        if (stringResponse.equals("successful")) {
            RedirectAttributes.addFlashAttribute("response", "Thêm mới loại sản phẩm thành công.");
            RedirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            RedirectAttributes.addFlashAttribute("response", stringResponse);
            RedirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/admin/sanpham/page/1";
    }

    @PostMapping("/admin/addhangsx")
    public String ThemMoiHangSX(@ModelAttribute HangSxEntity hangSX, RedirectAttributes redirectAttributes) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //chuyển Object sang JSON để POST đến API
        ObjectMapper objectMapper = new ObjectMapper();
        RequestBody requestBody = RequestBody.create(JSON, objectMapper.writeValueAsString(hangSX));
        //=========================================
        Request request = new Request.Builder().url("http://"+local+"/api/hangsxs/post").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        String stringResponse = response.body().string();
        if (stringResponse.equals("successful")) {
            redirectAttributes.addFlashAttribute("response", "Thêm mới hãng sản xuất thành công.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } else {
            redirectAttributes.addFlashAttribute("response", stringResponse);
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        return "redirect:/admin/sanpham/page/1";
    }


}
