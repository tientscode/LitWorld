package com.tscode.LitWorld.Service;


import org.springframework.stereotype.Service;

@Service
public class PageService {

    public int calculateTotalPages(int totalLength, int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Số đối tượng trong một trang không phù hợp");
        }
        return (int) Math.ceil((double) totalLength / pageSize);
    }

}
