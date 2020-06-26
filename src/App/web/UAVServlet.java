package App.web;

import App.Config;
import App.GroundTerritory.ListGroundTerritory;
import App.Model.Aircraft;
import App.Storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UAVServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();
    private List<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
    private ListGroundTerritory territory = new ListGroundTerritory(map, 3, 4);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("aircrafts", storage.getAllAircraftsSorted());
            request.setAttribute("current_aircrafts", storage.getAllAircraftsSorted());
            request.setAttribute("territory_matrix", territory);
            request.setAttribute("ground_objects", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Aircraft a = storage.get(id);
        switch (action) {
            case "delete":
                storage.delete(id);
                response.sendRedirect("UAV");
                break;
            case "up":
                a.moveUp(territory, storage, a);
                break;
            case "down":
                a.moveDown(territory, storage, a);
                break;
            case "left":
                a.moveLeft(territory, storage, a);
                break;
            case "right":
                a.moveRight(territory, storage, a);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        if (!action.equals("delete")) {
            request.setAttribute("aircrafts", storage.getAllSorted());
            request.setAttribute("territory_matrix", territory);
            response.sendRedirect("UAV");
        }
    }

}

