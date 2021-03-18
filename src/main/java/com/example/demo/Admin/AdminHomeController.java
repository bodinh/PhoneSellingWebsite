package com.example.demo.Admin;

import com.example.demo.Model.DoanhThu;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminHomeController {

    String local = "localhost:8080";

    @GetMapping("/admin/index")
    public String indexAdmin(Model model) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://" + local + "/api/doanhthu/").build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            List<DoanhThu> doanhThus = Arrays.asList(objectMapper.readValue(response.body().string(), DoanhThu[].class));
            model.addAttribute("yearLastest",doanhThus.get(0).getNam());
           doanhThus =  doanhThus.subList(1,doanhThus.size());
            model.addAttribute("year",doanhThus);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "admin/index";
    }
}
