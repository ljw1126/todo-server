package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

     /*
        - 1. todo 리스트 목록에 아이템 추가
        - 2. todo 리스트 목록 중 특정 아이템을 조회
        - 3. todo 리스트 전체 목록을 조회
        - 4. todo 리스트 목록 중 특정 아이템을 수정
        - 5. todo 리스트 목록 중 특정 아이템을 삭제
        - 6. todo 리스트 전체 목록을 삭제

        먼저 메소드 시그니처 정의
      */

      public TodoModel add(TodoRequest request){
          TodoModel todoModel = new TodoModel();
          todoModel.setTitle(request.getTitle());
          todoModel.setOrder(request.getOrder());
          todoModel.setCompleted(request.getCompleted());

          return this.todoRepository.save(todoModel);
      }

      public TodoModel searchById(Long id){
        return  this.todoRepository.findById(id)
                  .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
      }

      public List<TodoModel> searchAll(){
          return this.todoRepository.findAll();
      }

      public TodoModel updateById(Long id, TodoRequest request){
          TodoModel todoModel = this.searchById(id);

          if(request.getTitle() != null){
              todoModel.setTitle(request.getTitle());
          }

          if(request.getOrder() != null){
              todoModel.setOrder(request.getOrder());
          }

          if(request.getCompleted() != null){
              todoModel.setCompleted(request.getCompleted());
          }

          return this.todoRepository.save(todoModel); // 저장된 결과값 리턴
      }

      public void deleteById(Long id){
          this.todoRepository.deleteById(id);
      }

      public void deleteAll(){
          this.todoRepository.deleteAll();
      }

}
