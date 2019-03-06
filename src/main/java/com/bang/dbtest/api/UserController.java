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


/*    //
    @GetMapping("find/{userIdx}")
    public ResponseEntity getUser(@RequestParam("name") final Optional<String> name) {
        try {
            //name이 null일 경우 false, null이 아닐 경우 true
            if (name.isPresent()) return new ResponseEntity<>(userService.findByName(name.get()), HttpStatus.OK);
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/


    //User 저장
    @PostMapping("save")
    public ResponseEntity signup(
            SignUpReq signUpReq,
            @RequestPart(value = "voicefile1", required = false) final MultipartFile voicefile1,
            @RequestPart(value = "voicefile2", required = false) final MultipartFile voicefile2,
            @RequestPart(value = "voicefile3", required = false) final MultipartFile voicefile3,
            @RequestPart(value = "videofile", required = false)final MultipartFile videofile) {
        try {
            //파일을 signUpReq에 저장
            if (voicefile1 != null) signUpReq.setVoicefile1(voicefile1);
            if (voicefile1 != null) signUpReq.setVoicefile2(voicefile2);
            if (voicefile1 != null) signUpReq.setVoicefile3(voicefile3);
            if(videofile != null) signUpReq.setVideofile(videofile);
            return new ResponseEntity<>(userService.save(signUpReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //User 정보 수정
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

    //Delete VoiceFileUrl
    @PutMapping("voiceFile1/{userIdx}")
    public ResponseEntity deleteVoiceFile(
            @PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByVoiceFile(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete VideoFileUrl
    @PutMapping("videoFile/{userIdx}")
    public ResponseEntity deleteVideoFile(
            @PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByVideoFile(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //User 삭제
    @DeleteMapping("/{userIdx}")
    public ResponseEntity deleteUser(@PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByUserIdx(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    //User VoiceFile 삭제
    @DeleteMapping("/Voicefile/{userIdx}")
    public ResponseEntity deleteVoiceFile(@PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByVoiceFile(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //User VideoFile 삭제
    @DeleteMapping("/Videofile/{userIdx}")
    public ResponseEntity deleteVideoFile(@PathVariable(value = "userIdx") final int userIdx) {
        try {
            return new ResponseEntity<>(userService.deleteByVideoFile(userIdx), HttpStatus.OK);
        }catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}