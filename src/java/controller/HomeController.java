
package controller;

import dao.DBContext;
import dao.GalleryDAO;
import dao.IntroductionDAO;
import dao.ShareDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gallery;
import model.Introduction;
import model.Share;

/**
 * HomeController.<br>
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            DBContext db = new DBContext();
            String imagePath = db.getImagePath();
            GalleryDAO gd = new GalleryDAO();

            //get index page
            String page = request.getParameter("page");
            int indexPage = 1;
            if (page != null) {
                try {
                    indexPage = Integer.parseInt(page);
                } catch (NumberFormatException e) {
                    indexPage = -1;
                }
            }

            //number of item in a page
            int pageSize = 3;
            if (indexPage != -1) {
                //number of items
                int rowCount = gd.numberOfResult();
                //maximum of page
                int maxPage = rowCount / pageSize + (rowCount % pageSize > 0 ? 1 : 0);

                if (indexPage <= maxPage) {
                    List<Gallery> listGalleries = gd.pagging(indexPage, pageSize);
                    for (Gallery g : listGalleries) {
                        g.setImage(imagePath + g.getImage());
                    }
                    request.setAttribute("galleries", listGalleries);
                    request.setAttribute("maxPage", maxPage);
                    request.setAttribute("pageIndex", indexPage);
                } else {
                    request.setAttribute("mess", "Data not found! Because index page is out of rage");
                }
            } else {
                request.setAttribute("mess", "Data not found! Because index page is character");
            }
            
            // Get Intro
            IntroductionDAO introDAO = new IntroductionDAO();
            Introduction intro = introDAO.getIntroduction();
            if (intro == null) {
                request.setAttribute("errIntro", "Data Intro error!");
            } else {
                //set image for intro element
                intro.setImage(imagePath + intro.getImage());
                request.setAttribute("intro", intro);
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

            //set hight light when we stay home page
            request.setAttribute("clickedHome", true);

            request.getRequestDispatcher("./views/home.jsp").forward(request, response);
        } catch (Exception e) {
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
