package com.highfive.refurmoa.admin.dto.response;

import com.highfive.refurmoa.entity.Memo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoResponseDTO {
    private String content;

    public MemoResponseDTO(Memo memo) {
        this.content = memo.getMemoContent();
    }
}
