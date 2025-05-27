package com.ssafy.nomnom.model.dao;

import java.util.List;
import com.ssafy.nomnom.model.dto.Allergy;

public interface AllergyDao {
    List<Allergy> findAll();
}