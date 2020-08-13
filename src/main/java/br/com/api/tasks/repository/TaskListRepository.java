package br.com.api.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.tasks.model.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
