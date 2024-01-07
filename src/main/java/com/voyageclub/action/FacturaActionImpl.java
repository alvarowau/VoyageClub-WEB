package com.voyageclub.action;

import com.voyageclub.dao.FacturaDAO;
import com.voyageclub.model.Factura;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class FacturaActionImpl implements FacturaAction {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
