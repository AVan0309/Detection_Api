<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传测试页</title>
</head>
<body>
<form method="post" action="/addInputInfo" class="">
    <table>
<%--        <tr>--%>
<%--            <td>序号：</td>--%>
<%--            <td><input type="text" id="id"></td>--%>
<%--        </tr>--%>

        <tr>
            <td>四周前鸡舍鸡只数量：</td>
            <td><input type="text" id="A4"></td>
        </tr>
        <tr>
            <td>四周中死亡以及淘汰的鸡只数量：</td>
            <td><input type="text" id="M4"></td>
        </tr>
        <tr>
            <td>鸡群实际采食量：</td>
            <td><input type="text" id="AFI"></td>
        </tr>
        <tr>
            <td>鸡群预计的标准采食量：</td>
            <td><input type="text" id="FI"></td>
        </tr>
        <tr>
            <td>鸡舍内的鸡只数目：</td>
            <td><input type="text" id="A"></td>
        </tr>
        <tr>
            <td>标准饮水下限：</td>
            <td><input type="text" id="DW"></td>
        </tr>
        <tr>
            <td>标准饮水上限：</td>
            <td><input type="text" id="HW"></td>
        </tr>
        <tr>
            <td>实际饮水量：</td>
            <td><input type="text" id="AWI"></td>
        </tr>
        <tr>
            <td>周产蛋总数：</td>
            <td><input type="text" id="AWE"></td>
        </tr>
        <tr>
            <td>存栏蛋鸡数：</td>
            <td><input type="text" id="C"></td>
        </tr>
        <tr>
            <td>一周后存栏蛋鸡数：</td>
            <td><input type="text" id="AWC"></td>
        </tr>
        <tr>
            <td>畸形蛋的数量：</td>
            <td><input type="text" id="J"></td>
        </tr>
        <tr>
            <td>鸡舍内鸡只产蛋总数：</td>
            <td><input type="text" id="D"></td>
        </tr>
        <tr>
            <td>破损蛋的数量：</td>
            <td><input type="text" id="P"></td>
        </tr>
        <tr>
            <td>得分为1的鸡的百分率：</td>
            <td><input type="text" id="TZ1"></td>
        </tr>
        <tr>
            <td>得分为2的鸡的百分率：</td>
            <td><input type="text" id="TZ2"></td>
        </tr>
        <tr>
            <td>均匀度：</td>
            <td><input type="text" id="U"></td>
        </tr>
        <tr>
            <td>周龄：</td>
            <td><input type="text" id="W"></td>
        </tr>
        <tr>
            <td>该周龄平均蛋重：</td>
            <td><input type="text" id="WAEW"></td>
        </tr>
        <tr>
            <td>该周龄预计平均蛋重：</td>
            <td><input type="text" id="PAEW"></td>
        </tr>
        <tr>
            <td>日龄：</td>
            <td><input type="text" id="day"></td>
        </tr>
        <tr>
            <td>温度：</td>
            <td><input type="text" id="T"></td>
        </tr>
        <tr>
            <td>湿度：</td>
            <td><input type="text" id="H"></td>
        </tr>
        <tr>
            <td>二氧化碳：</td>
            <td><input type="text" id="CO2"></td>
        </tr>
        <tr>
            <td>氨气：</td>
            <td><input type="text" id="NH3"></td>
        </tr>
        <tr>
            <td><input type="submit"></td>
            <td><input type="reset"></td>
        </tr>
    </table>
</form>
</body>
</html>