package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ride> getRides() {
            
/*  //Static ArrayList method
    Ride ride = new Ride();
    ride.setName("Corner Canyon");
    ride.setDuration(120);
    List <Ride> rides = new ArrayList<>();
    rides.add(ride);
*/
        //jdbcTemplate read all record from DB
        List<Ride> rides = jdbcTemplate.query("select * from ride", new RowMapper<Ride> (){
            @Override
            public Ride mapRow(ResultSet rs, int i) throws SQLException {
                        
                Ride ride = new Ride();
                ride.setId(rs.getInt("id"));
                ride.setName(rs.getString("name"));
                ride.setDuration(rs.getInt("duration"));
                return ride;
            }     
        });
    
        return rides;
    }

    @Override
    public Ride createRide(Ride ride) {
        //#1 jdbc template method
        jdbcTemplate.update("insert into ride (name, duration) values (?,?)", ride.getName(), ride.getDuration());
        
/*      //#2 simple jdbc insert method
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("duration");
            
        insert.setTableName("ride");
        insert.setColumnNames(columns);
            
        Map<String, Object> data = new HashMap<>();
            
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());
            
        insert.setGeneratedKeyName("id");
            
        Number key = insert.executeAndReturnKey(data);
            
        System.out.println(key);
*/            

        //#3 

        return null;
    }

    @Override
    public Ride modifyRide(Ride ride) {
        
        //jdbcTemplate read all record from DB
        List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
        
        return null;
    }
     
}
