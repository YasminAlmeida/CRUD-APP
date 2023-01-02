package com.trainingcode.specification;
import com.trainingcode.entities.Task;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskSpecifications {

    public static Specification<Task> getByUserAndStatusAndPriorityAndCategory(Optional<Long> userId, Optional<Long>  statusId,
                                                                        Optional<Long>  priorityId, Optional<Long>  categoryId){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!userId.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId.get()));
            }
            if(!statusId.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("taskStatus"), statusId.get()));
            }
            if(!priorityId.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("priorities"), priorityId.get()));
            }
            if(!categoryId.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("category").get("id"), categoryId.get()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
