package graduation.petshop.domain.board.dto.response;


import graduation.petshop.domain.board.entity.Board;
import graduation.petshop.domain.board.entity.BoardTag;
import graduation.petshop.domain.board.entity.TagType;
import graduation.petshop.domain.board.entity.Comment;
import graduation.petshop.domain.file.entity.FileInfo;
import graduation.petshop.domain.member.entity.Member;

import java.util.List;

public class BoardResponse {

    private Long id;
    private String title;
    private String body;
    private int likeCount;
    private List<TagType> boardTags;
    private List<Comment> comments;
    private FileInfo fileInfo;
    private Member member;

    public static BoardResponse of(Board board) {
        BoardResponse response = new BoardResponse();
        response.id = board.getId();
        response.title = board.getTitle();
        response.body = board.getBody();
        response.likeCount = board.getLikeList().size();
        response.boardTags = board.getBoardTagList().stream().map(BoardTag::getTagType).toList();
        response.fileInfo = board.getFileInfo();
        response.comments = board.getComments();
        response.member = board.getMember();

        return response;
    }
}
