package org.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.example.database.DatabaseManager;
import org.example.entities.Medicine;
import org.example.repository.*;
import org.example.service.PharmacyService;
import org.example.service.PharmacyServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

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
            req.getRequestDispatcher("/WEB-INF/jsp/medicine-form.jsp").forward(req, resp);

        } else if ("edit".equals(action)) {
            String name = req.getParameter("name");
            Medicine medicine = pharmacyService.getMedicineByName(name);
            req.setAttribute("medicine", medicine);
            req.getRequestDispatcher("/WEB-INF/jsp/medicine-form.jsp").forward(req, resp);

        } else if ("delete".equals(action)) {
            String name = req.getParameter("name");
            pharmacyService.removeMedicine(name);
            resp.sendRedirect(req.getContextPath() + "/medicines");

        } else {
            List<Medicine> medicines = pharmacyService.getAllMedicines();
            req.setAttribute("medicines", medicines);
            req.getRequestDispatcher("/WEB-INF/jsp/medicines.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String dateStr = req.getParameter("expirationDate");
        String diseaseName = req.getParameter("diseaseName");
        String isEdit = req.getParameter("isEdit");

        LocalDate expirationDate = LocalDate.parse(dateStr);
        Medicine medicine = new Medicine(name, expirationDate, diseaseName);

        if ("true".equals(isEdit)) {
            pharmacyService.updateMedicine(name, medicine);
        } else {
            pharmacyService.addMedicine(medicine);
        }

        resp.sendRedirect(req.getContextPath() + "/medicines");
    }
}
