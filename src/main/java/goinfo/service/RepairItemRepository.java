package goinfo.service;


import goinfo.domain.RepairItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface RepairItemRepository extends CrudRepository<RepairItem, Long> {
//    List<Customer> findByLastName(String lastName);
}