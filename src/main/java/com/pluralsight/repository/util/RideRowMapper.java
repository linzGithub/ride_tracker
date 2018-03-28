/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight.repository.util;

import com.pluralsight.model.Ride;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author linzhang
 */
public class RideRowMapper implements RowMapper<Ride> {
    
    @Override
    public Ride mapRow(ResultSet rs, int i) throws SQLException {
                        
        Ride ride = new Ride();
        ride.setId(rs.getInt("id"));
        ride.setName(rs.getString("name"));
        ride.setDuration(rs.getInt("duration"));
        return ride;
    }     

}
