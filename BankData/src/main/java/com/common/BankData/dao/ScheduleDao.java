package com.common.BankData.dao;

import com.common.BankData.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ScheduleDao extends JpaRepository<Schedule,Integer> {


//    @Modifying
//    @Transactional
//    @Query(value="delete from schedule c where c.scheduleid = ?1")
//    void deleteBySchedule_id(int schedule_id);

//        @Modifying
//    @Transactional
    @Modifying
    @Transactional
 @Query(value="delete from schedule c where c.scheduleid = ?1")
    int removeByScheduleid(int scheduledid);

//    @Override
//    void deleteById(Integer integer){
//
//    } @Override
////    void deleteById(Integer integer){
////
////    }
}
