package com.brucecompiler.mapper;

import com.brucecompiler.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<AddressBook> list(AddressBook addressBook);

    void insert(AddressBook addressBook);

    AddressBook findById(Long id);
}
