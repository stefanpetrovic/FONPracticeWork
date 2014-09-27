/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.domain.core.Keywords;
import dao.exception.EngineDAOException;
import java.util.List;

/**
 *
 * @author Djole
 */
public interface KeywordsDAO extends BasePersistentDAO<Keywords, Long>{
    
    public List<Keywords> getKeywordsByKeywords(List<Keywords> keywords) throws EngineDAOException;
}
