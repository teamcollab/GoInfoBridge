package goinfo.service;


import goinfo.domain.RepairItem;
import org.springframework.data.repository.CrudRepository;

public interface RepairItemRepository extends CrudRepository<RepairItem, Long> {
//    List<Customer> findByLastName(String lastName);
}