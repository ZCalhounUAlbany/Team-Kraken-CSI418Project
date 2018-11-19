<%--
  Created by IntelliJ IDEA.
  User: hzeng
  Date: 11/6/2018
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Search Page</title>
        <script>    
        function logout_popup() {
            alert('you have logged out');
        }
        function getData_popup() {
            alert('getting data');
        }

        function displayContact() {
            var x = document.getElementById("contactInfo");
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }
        </script>
    
<style>
#extras {
    width: 400px;
    top: 250px;
    left: 30px;
    position: relative;
}
#extras input {
    float:left;
    margin:2%;
}
#search_box{
    width: 350px;
}

.extra-button {
    height:20px;
    width:100px;
    background: #4acfff;
    color: #fff;
    border: 1px solid #eee;
    border-radius: 20px;
    text-shadow:none;
}
.extra-button:hover {
    height:20px;
    width:100px;
    background: #016ABC;
    color: #fff;
    border: 1px solid #eee;
    border-radius: 20px;
    text-shadow:none;
}


/*Login page buttons, TODO if have time*/
#enter {
    position: relative;
}
#register {
    position: relative;
}

</style>
</head>
<body>
<div align="center">
    <form id="main_form" action="/search" method="Post">
        <h1>Dear: ${username}</h1>
        <h1>Welcome to Search Page</h1>
        <select name="category">
            <option placeholder="">Category</option>
            <option value="acquiredDate">Date</option>
            <option value="headline">Headline</option>
            <option value="symbol">Symbol</option>
            <option value="symbolText">Symbol Text</option>
        </select>
        <p></p>
        <input type="text" id="search_box" name="search_input" placeholder="Type to Search">
        <input type="submit" value="Search">
    </form>
    <div id="extras">
        <form action="/KrakenWebProject/getData" method="Post" onsubmit="getData_popup()">
            <input class="extra-button" type="submit" value="Acquire Data">
        </form>

        <form action="views/login.jsp" onsubmit="logout_popup()">
            <input class="extra-button" type="submit" value="Logout">
        </form>
    </div>

    </div>
</body>
</html>
