package DAO;

import java.util.List;

public interface Dao<T, E> {

  //Lấy toàn bộ danh sách đối tượng 
  List<T> getAll() ;

  //Lấy đối tượng theo ID
  T getByID(E e);

  //Thêm mới đối tượng
  boolean Add(T t);

  //Xóa đối tượng
  boolean Delete(E t);

  
}