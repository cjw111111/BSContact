package Servlet;

import JDBC.JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {
    private JDBC jdbc;

    public void init() {
        jdbc = new JDBC();
    }

    public void destroy() {
        jdbc.finalize();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("view")) {
            String query = request.getParameter("query");
            viewContact(request, response, query);
        } else if (operation.equals("add")) {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            addContact(request, response, name, phone, email, address);
        } else if (operation.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String field = request.getParameter("field");
            String value = request.getParameter("value");
            updateContact(request, response, id, field, value);
        } else if (operation.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            deleteContact(request, response, id);
        }
    }

    private void viewContact(HttpServletRequest request, HttpServletResponse response, String query) throws IOException {
        try {
            ResultSet rs1 = jdbc.select_name(query);
            ResultSet rs2 = jdbc.select_phone(query);

            PrintWriter out = response.getWriter();

            if (rs1 != null) {
                while (rs1.next()) {
                    out.println("id:" + rs1.getString(1) + "<br>" + "姓名:" + rs1.getString(2) + "<br>" + "电话:" + rs1.getString(3) + "<br>" + "邮箱:" + rs1.getString(4) + "<br>" + "地址:" + rs1.getString(5) + "<br><br>");
                }
            }
            if (rs2 != null) {
                while (rs2.next()) {
                    out.println("id:" + rs2.getString(1) + "<br>" + "姓名:" + rs2.getString(2) + "<br>" + "电话:" + rs2.getString(3) + "<br>" + "邮箱:" + rs2.getString(4) + "<br>" + "地址:" + rs2.getString(5) + "<br><br>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addContact(HttpServletRequest request, HttpServletResponse response, String name, String phone, String email, String address) throws IOException {
        PrintWriter out = response.getWriter();

        if (jdbc.insert(name, phone, email, address)) {
            out.println("添加成功");
        } else {
            out.println("添加失败");
        }
    }

    private void updateContact(HttpServletRequest request, HttpServletResponse response, int id, String field, String value) throws IOException {
        PrintWriter out = response.getWriter();

        try {
            if (field.equals("name")) {
                jdbc.update_name(id, value);
            } else if (field.equals("phone")) {
                jdbc.update_phone(id, value);
            } else if (field.equals("email")) {
                jdbc.update_email(id, value);
            } else if (field.equals("address")) {
                jdbc.update_address(id, value);
            }

            out.println("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("修改失败");
        }
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response, int id) throws IOException {
        PrintWriter out = response.getWriter();

        if (jdbc.delete(id)) {
            out.println("删除成功");
        } else {
            out.println("删除失败");
        }
    }
}
