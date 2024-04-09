package com.rod.api.common.service;

import com.rod.api.common.component.Messenger;

public interface CommandService<T> {

    Messenger save(T t);

    Messenger deleteById(Long id);

    Messenger modify(T t);


}
