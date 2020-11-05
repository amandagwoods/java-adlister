import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListAdsServlet")
public class ListAdsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //use list
        List<Ad> ads = DaoFactory.getAdsDao().all();
        //pass it along
        request.setAttribute("ads", ads);
        request.getRequestDispatcher("/ads/index.jsp").forward(request,response);
    }
}
