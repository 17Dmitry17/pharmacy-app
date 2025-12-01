package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.database.DatabaseManager;
import org.example.entities.Disease;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/diseases")
public class DiseaseServlet extends HttpServlet {

    private PharmacyService pharmacyService;

    @Override
    public void init() throws ServletException {
        DatabaseManager.initializeDatabase();

        MedicineRepository medicineRepo = new MedicineRepositoryJdbc();
        DiseaseRepository diseaseRepo = new DiseaseRepositoryJdbc();
        SymptomRepository symptomRepo = new SymptomRepositoryJdbc();
        DiseaseSymptomRepository dsRepo = new DiseaseSymptomRepositoryJdbc();

        pharmacyService = new PharmacyServiceImpl(medicineRepo, diseaseRepo, symptomRepo, dsRepo);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("new".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/disease-form.jsp").forward(req, resp);

        } else if ("edit".equals(action)) {
            String name = req.getParameter("name");
            Disease disease = pharmacyService.getDiseaseByName(name);
            req.setAttribute("disease", disease);
            req.getRequestDispatcher("/WEB-INF/jsp/disease-form.jsp").forward(req, resp);

        } else {
            List<Disease> diseases = pharmacyService.getAllDiseases();
            req.setAttribute("diseases", diseases);
            req.getRequestDispatcher("/WEB-INF/jsp/diseases.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        Disease disease = new Disease(name, description);
        pharmacyService.addDisease(disease);

        resp.sendRedirect(req.getContextPath() + "/diseases");
    }
}
