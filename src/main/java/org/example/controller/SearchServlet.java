package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.database.DatabaseManager;
import org.example.entities.Disease;
import org.example.entities.Medicine;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
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
        String searchType = req.getParameter("type");

        if ("diseasesBySymptom".equals(searchType)) {
            String symptomName = req.getParameter("symptomName");
            if (symptomName != null && !symptomName.trim().isEmpty()) {
                List<Disease> diseases = pharmacyService.findDiseasesBySymptom(symptomName);
                req.setAttribute("diseases", diseases);
                req.setAttribute("symptomName", symptomName);
            }
        } else if ("medicinesByDisease".equals(searchType)) {
            String diseaseName = req.getParameter("diseaseName");
            if (diseaseName != null && !diseaseName.trim().isEmpty()) {
                List<Medicine> medicines = pharmacyService.findMedicinesByDisease(diseaseName);
                req.setAttribute("medicines", medicines);
                req.setAttribute("diseaseName", diseaseName);
            }
        }

        req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
    }
}
