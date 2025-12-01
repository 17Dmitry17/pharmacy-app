package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.database.DatabaseManager;
import org.example.entities.Symptom;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/symptoms")
public class SymptomServlet extends HttpServlet {

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
            req.getRequestDispatcher("/WEB-INF/jsp/symptom-form.jsp").forward(req, resp);

        } else {
            List<Symptom> symptoms = pharmacyService.getAllSymptoms();
            req.setAttribute("symptoms", symptoms);
            req.getRequestDispatcher("/WEB-INF/jsp/symptoms.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");

        Symptom symptom = new Symptom(name);
        pharmacyService.addSymptom(symptom);

        resp.sendRedirect(req.getContextPath() + "/symptoms");
    }
}
