package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.database.DatabaseManager;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;
import java.io.IOException;

@WebServlet("/link")
public class LinkServlet extends HttpServlet {
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
        req.getRequestDispatcher("/WEB-INF/jsp/link-disease-symptom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String diseaseName = req.getParameter("diseaseName");
        String symptomName = req.getParameter("symptomName");

        pharmacyService.linkDiseaseWithSymptom(diseaseName, symptomName);
        resp.sendRedirect(req.getContextPath() + "/search");
    }
}
