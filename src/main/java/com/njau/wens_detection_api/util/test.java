package com.njau.wens_detection_api.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {


    public static void main(String[] args) throws IOException {
        String houseId = "238";//蛋鸡3舍
        String houseId2 = "460";//蛋鸡1舍
        String houseId3 = "461";//蛋鸡2舍
        String houseId4 = "544";//蛋鸡4舍
        Device_Propertys(houseId);
        Device_Propertys(houseId2);
        Device_Propertys(houseId3);
        Device_Propertys(houseId4);
    }


    public static String Access_Token() throws IOException {
        URL url = new URL("http://data.znmc.co/open/api/auth/token/get");

        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        // 准备JSON数据
        JSONObject jsonData = new JSONObject();
        jsonData.put("appId", "njlkqy_api");
        jsonData.put("appSecret", "d8b13c8aa59bf1449780f937d92c6259");

        // 将JSON数据写入输出流
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonData.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // 读取响应
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // 解析返回的JSON并获取accessToken
            JSONObject jsonObject = new JSONObject(new JSONTokener(response.toString()));
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String accessToken = dataObject.getString("accessToken");
            return accessToken;
        }
    }




    public static void Device_Propertys(String houseId) {

        String apiUrl = "http://data.znmc.co/open/api/house/device/propertys";

        String accessToken = "";
        String value = null;
        try {
            accessToken = Access_Token();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String authHeader = "TOKEN " + accessToken;

        try {
            // 创建URL对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Authorization", authHeader);
            connection.setDoOutput(true);

            // 准备JSON数据
            JSONObject jsonData = new JSONObject();
            jsonData.put("houseId", houseId);

            // 将JSON数据写入输出流
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 读取响应
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                JSONObject jsonObject = new JSONObject(new JSONTokener(response.toString()));
                JSONArray dataArray = jsonObject.getJSONArray("data");
//                JSONObject dataObject = dataArray.getJSONObject(0); // 假设只有一个data对象
//                JSONArray propertysArray = dataObject.getJSONArray("propertys");


                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataObject = dataArray.getJSONObject(i);

                    // 获取propertys数组
                    JSONArray propertysArray = dataObject.getJSONArray("propertys");
                    String[] resultStrings = new String[propertysArray.length()+2];
                    // 遍历propertys数组中的每个温度属性
                    for (int j = 0; j < propertysArray.length(); j++) {
                        JSONObject propertyObject = propertysArray.getJSONObject(j);

                        // 检查是否包含value字段，并提取它
                        if (propertyObject.has("value")) {
                            value = propertyObject.getString("value");
                            if (value.isEmpty()) {
                                // 如果为空，则赋值为null
                                value = "null";
                            }
//                            System.out.println(value);
                            resultStrings[j] = value;
                        }


                    }
                    LocalDateTime current = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = current.format(formatter);
                    resultStrings[111] = formattedDateTime;
                    resultStrings[112] = houseId;
//                    System.out.println(resultStrings.length);
                    insertIntoTable(resultStrings);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void insertIntoTable (String[] value) {

        String DB_URL = "jdbc:mysql://121.199.0.105:3306/wens_sub_health";
        String USER = "root";
        String PASS = "njau#B307";

        try {
            // 加载并注册JDBC驱动
//                Class.forName("com.mysql.cj.jdbc.Driver");
            StringBuilder sql = new StringBuilder("INSERT INTO all_info_xiaojuren (");
            for (int i = 0; i < value.length; i++) {
                sql.append("T").append(i + 1).append(", ");
            }
            // 移除最后的逗号和空格
            sql.setLength(sql.length() - 2);
            sql.append(") VALUES (");
            for (int i = 0; i < value.length; i++) {
                sql.append("?, ");
            }
            sql.setLength(sql.length() - 2); // 移除最后的逗号和空格
            sql.append(")");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                // 创建PreparedStatement对象
                preparedStatement = connection.prepareStatement(sql.toString());
                // 设置参数
                for (int i = 0; i < value.length; i++) {
                    preparedStatement.setString(i + 1, value[i]); // 注意索引从1开始
                }
                // 执行插入操作
                preparedStatement.executeUpdate();



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // 关闭资源
                try {

                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



