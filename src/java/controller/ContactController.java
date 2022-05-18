
package controller;

import dao.DBContext;
import dao.GalleryDAO;
import dao.InformationDAO;
import dao.ShareDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gallery;
import model.Information;
import model.Share;

/**
 * 
 * @author CoD
 */
@WebServlet(name = "ContactController", urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            // Get Image Path
            DBContext db = new DBContext();
            String imagePath = db.getImagePath();
            
            // Get Contact
            InformationDAO idao = new InformationDAO();
            Information info = idao.getInfor();
            info.setImage(imagePath + info.getImage());
            request.setAttribute("info", info);
            
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
            //set hight light when we stay contact page
            request.setAttribute("clickedContact", true);
            request.getRequestDispatcher("./views/contact.jsp").forward(request, response);
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
