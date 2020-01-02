<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="syl" uri="http://syl.pager-tags" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/admin/css/pager.css" rel="stylesheet" type="text/css"/>
    <script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
    <script type="text/javascript">
        function addProduct() {
            window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
        }
    </script>
</HEAD>
<body>
<br>
<form id="Form1" name="Form1"
      action="${pageContext.request.contextPath}/admin/products/findProduct.do"
      method="post">
    <table cellSpacing="1" cellPadding="0" width="100%" align="center"
           bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3">
                <strong>查 询 条 件</strong>
            </td>
        </tr>
        <tr>
            <td>
                <table cellpadding="0" cellspacing="0" border="0" width="100%">
                    <tr>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            商品编号
                        </td>
                        <td class="ta_01" bgColor="#ffffff"><input type="text"
                                                                   name="id" size="15" value="${id}" id="Form_userName"
                                                                   class="bg" />
                        </td>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            类别：
                        </td>
                        <td class="ta_01" bgColor="#ffffff"><select name="category"
                                                                    id="category">
                            <option value="" name="category" <c:if test="${category==null}">selected</c:if> >--选择商品类别--</option>
                            <option value="文学" <c:if test="${category=='文学'}">selected</c:if>>文学</option>
                            <option value="生活" <c:if test="${category=='生活'}">selected</c:if>>生活</option>
                            <option value="计算机" <c:if test="${category=='计算机'}">selected</c:if>>计算机</option>
                            <option value="外语" <c:if test="${category=='外语'}">selected</c:if>>外语</option>
                            <option value="经营" <c:if test="${category=='经营'}">selected</c:if>>经营</option>
                            <option value="励志" <c:if test="${category=='励志'}">selected</c:if>>励志</option>
                            <option value="社科" <c:if test="${category=='社科'}">selected</c:if>>社科</option>
                            <option value="学术" <c:if test="${category=='学术'}">selected</c:if>>学术</option>
                            <option value="少儿" <c:if test="${category=='少儿'}">selected</c:if> >少儿</option>
                            <option value="艺术" <c:if test="${category=='艺术'}">selected</c:if>>艺术</option>
                            <option value="原版" <c:if test="${category=='原版'}">selected</c:if>>原版</option>
                            <option value="科技" <c:if test="${category=='科技'}">selected</c:if>>科技</option>
                            <option value="考试" <c:if test="${category=='考试'}">selected</c:if>>考试</option>
                            <option value="生活百科" <c:if test="${category=='生活百科'}">selected</c:if>s>生活百科</option>
                        </select></td>
                    </tr>

                    <tr>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            商品名称：
                        </td>
                        <td class="ta_01" bgColor="#ffffff"><input type="text"
                                                                   name="name" size="15" value="${product.name}" id="Form1_userName"
                                                                   class="bg"/>
                        </td>
                        <td height="22" align="center" bgColor="#f5fafe" class="ta_01">
                            价格区间(元)：
                        </td>
                        <td class="ta_01" bgColor="#ffffff"><input type="text"
                                                                   name="minPrice" size="10" value="${minPrice}"/>- <input
                                type="text"
                                name="maxPrice" size="10" value="${maxPrice}"/></td>
                    </tr>

                    <tr>
                        <td width="100" height="22" align="center" bgColor="#f5fafe"
                            class="ta_01"></td>
                        <td class="ta_01" bgColor="#ffffff"><font face="宋体"
                                                                  color="red"> &nbsp;</font>
                        </td>
                        <td align="right" bgColor="#ffffff" class="ta_01"><br>
                            <br></td>
                        <td align="right" bgColor="#ffffff" class="ta_01">
                            <button type="submit" id="search" name="search"
                                    value="&#26597;&#35810;" class="button_view">
                                &#26597;&#35810;
                            </button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
                                type="reset" name="reset" value="&#37325;&#32622;"
                                class="button_view"/>
                        </td>
                    </tr>
                </table>
            </td>

        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3"><strong>商 品 列 表</strong>
            </TD>
        </tr>
        <tr>
            <td class="ta_01" align="right">
                <button type="button" id="add" name="add" value="&#28155;&#21152;"
                        class="button_add" onclick="addProduct()">&#28155;&#21152;
                </button>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="center" bgColor="#f5fafe">
                <table cellspacing="0" cellpadding="1" rules="all"
                       bordercolor="gray" border="1" id="DataGrid1"
                       style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                    <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                        <td align="center" width="24%">商品编号</td>
                        <td align="center" width="18%">商品名称</td>
                        <td align="center" width="9%">商品价格</td>
                        <td align="center" width="9%">商品数量</td>
                        <td width="8%" align="center">商品类别</td>
                        <td width="8%" align="center">编辑</td>
                        <td width="8%" align="center">删除</td>
                    </tr>

                    <c:forEach items="${products}" var="p">
                        <tr onmouseover="this.style.backgroundColor = 'white'"
                            onmouseout="this.style.backgroundColor = '#F5FAFE';">
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="200">${p.id }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="18%">${p.name }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${p.price }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center"
                                width="8%">${p.pnum }</td>
                            <td style="CURSOR: hand; HEIGHT: 22px" align="center">
                                    ${p.category}</td>
                            <td align="center" style="HEIGHT: 22px" width="7%"><a
                                    href="${pageContext.request.contextPath}/admin/products/updateProductById.do?id=${p.id}&type=admin">
                                <img
                                        src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
                                        border="0" style="CURSOR: hand"> </a>
                            </td>

                            <td align="center" style="HEIGHT: 22px" width="7%"><a
                                    href="${pageContext.request.contextPath}/admin/products/deleteProductById.do?id=${p.id}">
                                <img
                                        src="${pageContext.request.contextPath}/admin/images/i_del.gif"
                                        width="16" height="16" border="0" style="CURSOR: hand">
                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <syl:pager pageIndex="${pageModel.pageIndex}"  pageSize="${pageModel.pageSize}"  recordCount="${pageModel.recordCount}" submitUrl="${pageContext.request.contextPath}/admin/products/findProduct.do?pageIndex={0}&category=${category}&maxPrice=${maxPrice}&minPrice=${minPrice}&name=${product.name}"/>
            </td>
        </tr>
        </TBODY>
    </table>
</form>
</body>
</HTML>

