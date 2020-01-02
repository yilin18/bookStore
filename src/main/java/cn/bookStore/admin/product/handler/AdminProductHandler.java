package cn.bookStore.admin.product.handler;

import cn.bookStore.admin.product.service.IAdminProductService;
import cn.bookStore.commons.beans.Product;
import cn.bookStore.commons.beans.ProductList;
import cn.bookStore.utils.PageModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/21
 */
@Controller
@RequestMapping("/admin/products")
public class AdminProductHandler {
    @Autowired
    private IAdminProductService productService;

    @RequestMapping("/findProduct.do")
    public String findProduct(Model model, Product product, Integer maxPrice, Integer minPrice, @RequestParam(defaultValue = "1") int pageIndex) {
        PageModel pageModel = new PageModel();
        pageModel.setPageSize(6);
        int count = productService.findProductCount(product, pageModel, maxPrice, minPrice);
        pageModel.setRecordCount(count);
        pageModel.setPageIndex(pageIndex);
        List<Product> products = productService.findProduct(product, pageModel, maxPrice, minPrice);
        model.addAttribute("products", products);
        model.addAttribute("pageModel", pageModel);
        model.addAttribute("id", product.getId());
        model.addAttribute("category", product.getCategory());
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        //System.out.println(products);
        return "/admin/products/list.jsp";
    }

    @RequestMapping("updateProductById.do")
    public String updateProductById(String type, MultipartFile upload, HttpServletRequest request, Model model, Product product, String id) throws IOException {
        if (("admin").equals(type)) {
            product = productService.findProductById(id);
            model.addAttribute("p", product);
            return "/admin/products/edit.jsp";
        }
        if (upload != null) {
            String webPath = "/productImg/";
            String path = request.getSession().getServletContext().getRealPath("/productImg/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String random = String.valueOf(System.currentTimeMillis());
            String fileName = random + upload.getOriginalFilename();
            upload.transferTo(new File(path, fileName));
            product.setImgurl(webPath + fileName);
        }
        int row = productService.modifyProductById(product);
        if (row > 0)
            return "redirect:/admin/products/findProduct.do";
        else
            return "/client/fail.jsp";
    }

    @RequestMapping("addProduct.do")
    public String addProduct(Product product, HttpServletRequest request, MultipartFile upload) throws IOException {
        String webPath = "/productImg/";
        String path = request.getSession().getServletContext().getRealPath("/productImg/");
        // System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String random = String.valueOf(System.currentTimeMillis());
        String fileName = random + upload.getOriginalFilename();

        upload.transferTo(new File(path, fileName));
        product.setId(random);
        product.setImgurl(webPath + fileName);
        int row = productService.addProduct(product);
        if (row > 0)
            return "redirect:/admin/products/findProduct.do";
        else
            return "/client/fail.jsp";
    }

    @RequestMapping("/deleteProductById.do")
    public String deleteProductById(String id) {
        int row = productService.deleteProductById(id);
        if (row > 0)
            return "redirect:/admin/products/findProduct.do";
        else
            return "/client/fail.jsp";
    }

    @RequestMapping("download.do")
    public void download(Integer year,HttpServletRequest request, Integer month, HttpServletResponse response) throws IOException {
        List<ProductList> productLists = productService.findProductList(year, month);
        // for (ProductList productList : productLists) {
        //
        // }
        String fileName = year + "年" + month + "月销售榜单";
        String sheetName = month + "月销售榜单";
        String titleName = "销售榜单";
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        cell1.setCellValue(titleName);
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2 = row2.createCell(0);
        HSSFCell cell3 = row2.createCell(1);
        cell2.setCellValue("书名");
        cell3.setCellValue("销量");
        for (int i = 0; i < productLists.size(); i++) {
            row2 = sheet.createRow(i + 2);
            for (int j = 0; j < 2; j++) {
                if (j == 0)
                    row2.createCell(j).setCellValue(productLists.get(i).getName());
                else
                    row2.createCell(j).setCellValue(productLists.get(i).getSaleNum());
            }

        }
        String filename = fileName + ".xls";
        filename=processFileName(filename,request);
        response.setContentType("application/ms-excel, charset=utf-8");
        response.setHeader("content-Disposition","attachment;filename="+filename);
        OutputStream os =response.getOutputStream();
        wb.write(os);

    }
    //解决下载中文文件名乱码问题
    public String processFileName(String filename, HttpServletRequest request) {
        String codeFileName = null;
        String agent = request.getHeader("USER-AGENT");
        try {
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
                //IE需要使用URL编码
                String name = java.net.URLEncoder.encode(filename, "UTF-8");
                codeFileName = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                //火狐或chrome直接还原编码格式为iso-8859-1
                codeFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeFileName;
    }
}
