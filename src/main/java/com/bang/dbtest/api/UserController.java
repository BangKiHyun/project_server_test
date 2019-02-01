package com.bang.dbtest.api;

import com.bang.dbtest.dto.User;
import com.bang.dbtest.mapper.UserMapper;
import com.bang.dbtest.model.SignUpReq;
import com.bang.dbtest.serviece.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.bang.dbtest.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

/*    //이름으로 찾기
    @GetMapping("")
    public ResponseEntity getUser(@RequestParam("name") final Optional<String> name) {
        try {
            //name이 null일 경우 false, null이 아닐 경우 true
            if(name.isPresent()) return new ResponseEntity<>(userService.findByName(name.get()), HttpStatus.OK);
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    @PostMapping("save")
    public ResponseEntity signup(
            SignUpReq signUpReq,
            @RequestPart(value = "voicefile", required = false) final MultipartFile voicefile,
            @RequestPart(value = "videofile", required = false)final MultipartFile videofile) {
        try {
            //파일을 signUpReq에 저장
            if (voicefile != null) signUpReq.setVoicefile(voicefile);
            if(videofile != null) signUpReq.setVideofile(videofile);
            return new ResponseEntity<>(userService.save(signUpReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userIdx}")
    public ResponseEntity signUp(
            @PathVariable(value = "userIdx") final int userIdx,
            @RequestBody final User user) {
        try {
            return new ResponseEntity<>(userService.update(userIdx, user), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userIdx}")
    public ResponseEntity deleteUser(@PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByUserIdx(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}