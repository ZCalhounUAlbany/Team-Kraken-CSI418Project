<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<%
    ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
    results = (ArrayList<ArrayList<String>>)request.getAttribute("results");
%>
<head>
    <script src="https://github.com/clarketm/FileSaver.js/blob/master/FileSaver.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/TableExport/3.3.13/js/tableexport.js"></script>
    <script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/2.3.5/jspdf.plugin.autotable.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="js/table2csv.js"></script>
    <script src="js/autotable.js"></script>
    <script src="https://cdn.rawgit.com/rainabba/jquery-table2excel/1.1.0/dist/jquery.table2excel.min.js"></script>
    <title>Result</title>
</head>
<body>
<div>
<h1>your result is here:</h1>
    <button id="btnExcel">Export Excel</button>
    <button id="btnCSV">Export CSV</button>
    <button id="btnPDF">Export PDF</button>
    <a href="views/searchpage.jsp"><button>Reset Search</button></a>
</div>
<div id="datadiv" align="center">
    <table id="rsTable" border="1">
        <thead>
        <tr>
            <td>ID</td>
            <td>Acquired Date</td>
            <td>headline</td>
            <td>symbol</td>
            <td>text</td>
        </tr>
        </thead>
        <tbody>
        <% for(int i = 0; i < results.size(); i++) {
            ArrayList<String> result = new ArrayList<String>();
            result = results.get(i);
        %>
        <tr>
            <td width="3%"><%=result.get(0)%></td>
            <td width="13%"><%=result.get(1)%></td>
            <td width="32%"><%=result.get(2)%></td>
            <td width="3%"><%=result.get(3)%></td>
            <td width="49%" style="font-size: 10px"><%=result.get(4)%></td>
        </tr>
        <%
            };
        %>
        </tbody>
    </table>
</div>
<script>
    $('#btnExcel').click(function(){
        $("#rsTable").table2excel({
            filename: "Data.xls",
        });
    });
    $('#btnCSV').click(function(){
        $("#rsTable").table2csv({
            filename: "Data.csv",
        });
    });
    $('#btnPDF').click(function generatepdf() {
        var doc = new jsPDF('p', 'pt','letter');
        var elem = document.getElementById("rsTable");
        var res = doc.autoTableHtmlToJson(elem);
        doc.autoTable(res.columns, res.data, {styles:{overflow:'linebreak',columnWidth: 'wrap'},
            columnStyles: {
                0: {columnWidth: 30},
                1: {columnWidth: 80},
                2: {columnWidth: 130},
                3: {columnWidth: 50},
                4: {columnWidth: 250}
            }});
        doc.save("Data.pdf")
    });

</script>
</body>
</html>
