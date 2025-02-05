package com.sparta.ticketing.repository.comment;

import com.sparta.ticketing.dto.comment.CommentRequest;
import com.sparta.ticketing.dto.comment.CommentUpdateRequest;
import com.sparta.ticketing.entity.Board;
import com.sparta.ticketing.entity.Comment;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.service.board.BoardConnectorInterface;
import com.sparta.ticketing.service.comment.CommentConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentConnectorInterfaceImpl implements CommentConnectorInterface {

    private final CommentRepository commentRepository;
    private final UserConnectInterface userConnectInterface;
    private final BoardConnectorInterface boardConnectorInterface;

    @Override
    @Transactional
    public Comment addComment(CommentRequest commentRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Board board = boardConnectorInterface.findById(commentRequest.getBoardId());
        Comment comment = Comment.from(commentRequest, user, board);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getMyComment(Long id) {
        return commentRepository.findAllByUserId(id);
    }

    @Override
    public List<Comment> findAllByBoardId(Long boardId) {
        return commentRepository.findAllByBoardId(boardId);
    }

    @Override
    public Comment updateComment(CommentUpdateRequest commentUpdateRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Comment comment = findById(commentUpdateRequest.getCommentId());
        extracted(comment, user);
        if (!comment.getContents().equals(commentUpdateRequest.getContents())) {
            comment.updateContents(commentUpdateRequest.getContents());
        }

        return comment;
    }

    @Override
    public void deleteComment(Long commentId, Long id) {
        User user = userConnectInterface.findById(id);
        Comment comment = findById(commentId);
        extracted(comment, user);
        commentRepository.delete(comment);
    }

    private Comment findById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글 못찾음"))
    }

    private static void extracted(Comment comment, User user) {
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("댓글에 접근권한없음");
        }
    }
}
