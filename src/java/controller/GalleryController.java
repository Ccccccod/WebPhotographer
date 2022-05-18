/*
 * GalleryController.java
 * 
 * All Rights Reserved 
 * Copyright (c) 2019 FPT University
 */
package controller;

import dao.DBContext;
import dao.GalleryDAO;
import dao.ImageGalleryDAO;
import dao.ShareDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gallery;
import model.ImageGallery;
import model.Share;

/**
 * 
 * @author CoD
 */
@WebServlet(name = "GalleryController", urlPatterns = {"/GalleryController"})
public class GalleryController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("id", id);
            } catch (NumberFormatException e) {
                id = -1;
            }

            // Get Image Path
            DBContext db = new DBContext();
            String imagePath = db.getImagePath();

            // Get images
            ImageGalleryDAO igd = new ImageGalleryDAO();

            //begin of get txtSearch and pageRow
            String txtPage = request.getParameter("txtPage");
            int indexPage = 1;
            if (txtPage != null) {
                try {
                    indexPage = Integer.parseInt(txtPage);
                } catch (Exception e) {
                    indexPage = -1;
                }
            }
            //end of get txtSearch and pageRow
            int pageSize = 5;

            if (indexPage != -1 && id != -1) {
                Gallery gal = new GalleryDAO().getGalleryByID(id);
                gal.setImage(imagePath + gal.getImage());
                int rowCount = igd.numberOfResult(id);
                int maxPage = (int) Math.ceil((double)rowCount / pageSize);
                System.out.println("mL"+maxPage);
                if (indexPage <= maxPage) {
                    List<ImageGallery> listImageGallary = igd.pagging(indexPage, pageSize, id);
                    for (ImageGallery g : listImageGallary) {
                        g.setImage(imagePath + g.getImage());
                    }
                    request.setAttribute("imgs", listImageGallary);
                    request.setAttribute("maxPage", maxPage);
                    request.setAttribute("pageIndex", indexPage);
                    request.setAttribute("gal", gal);
                } else {
                    request.setAttribute("message", "This page not found");
                }
            } else {
                request.setAttribute("message", "This page not found!");
            }

            //Get Share
            ShareDAO sh = new ShareDAO();
            List<Share> shs = sh.getShare();
            if (shs.isEmpty()) {
                request.setAttribute("errShare", "Data share error");
            } else {
                //set image for each share element
                for (Share s : shs) {
                    s.setIcon(imagePath + s.getIcon());
                }
                request.setAttribute("shs", shs);
            }

            List<Gallery> listGalleries = new GalleryDAO().getTopGallery(3);

            if (listGalleries.isEmpty()) {
                request.setAttribute("mess", "Data  Fail !!!");
            } else {
                request.setAttribute("galleries", listGalleries);
            }

            request.getRequestDispatcher("./views/gallery.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("./views/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
