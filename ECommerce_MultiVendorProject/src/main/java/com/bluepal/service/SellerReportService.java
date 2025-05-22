package com.bluepal.service;


import java.util.List;
import java.util.Optional;

import com.bluepal.model.Seller;
import com.bluepal.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport( SellerReport sellerReport);

}
