package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ride> getRides() {
            
/*      //#1 Static ArrayList method
        Ride ride = new Ride();
        ride.setName("Corner Canyon");
        ride.setDuration(120);
        List <Ride> rides = new ArrayList<>();
        rides.add(ride);
*/
/*        //#2 jdbcTemplate read all record from DB
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
*/
        //#3 jdbcTemplate read all record from DB with RowMapper util
        List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());
        
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
        
        //#1 jdbc template method
        //jdbcTemplate.update("insert into ride (name, duration) values (?,?)", ride.getName(), ride.getDuration());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                
                PreparedStatement ps = con.prepareStatement("insert into ride (name, duration) values (?,?)", new String[] {"id"});
                ps.setString(1, ride.getName());
                ps.setInt(2, ride.getDuration());
                return ps;
            }
        }, keyHolder);
     
        Number id = keyHolder.getKey();
        
        return getRide(id.intValue());
    }
    
    @Override
    public Ride getRide(Integer id) {
        Ride ride = jdbcTemplate.queryForObject("select * from ride where id = ?", new RideRowMapper(), id);
        
        return ride;
    }

    @Override
    public Ride updateRide(Ride ride) {
        
        jdbcTemplate.update("update ride set name = ?, duration= ? where id = ?", ride.getName(), ride.getDuration(), ride.getId());
                
        return ride;
    }

    @Override
    public void updateRides(List<Object[]> pairs) {
        
        jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);
    }

    @Override
    public void deleteRide(Integer id) {
        //#1 delete entry by id
        //jdbcTemplate.update("delete from ride where id = ?", id);
        
        // #2 ParamMap 
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        
        namedTemplate.update("delete from ride where id = :id", paramMap);
    }
    
    
     
}
