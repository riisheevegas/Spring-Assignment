package com.rishi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rishi.entities.FormDetailsSKill;
import com.rishi.entities.PhotoFile;
import com.rishi.entities.UserSkills;
import com.rishi.service.FormDetailsService;
import com.rishi.service.PhotoMasterService;
import com.rishi.service.SkillService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

@Controller
public class FormDetailsController {
	  @Autowired
	    private FormDetailsService userDetailsService;

	    @Autowired
	    private SkillService skillService;

	    @Autowired
	    private PhotoMasterService fileMasterService;

	    @GetMapping("/")
	    public String showForm()
	    {
	        return "user_details_form";
	    }


	    @GetMapping("/getfile/{id}")
	    public ResponseEntity files(@PathVariable("id") int id) throws Exception
	    {
	        PhotoFile file_master = fileMasterService.getFileMasterById(id);
	        if(file_master==null)
	        {
	            return ResponseEntity.badRequest().body("file not found");
	        }
	        byte[] image = Files.readAllBytes(Paths.get(file_master.getFileDirectory()));
	        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	    }


	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(BindException.class)
	    public ModelAndView handleValidationExceptions(
	            BindException ex) {
	        ModelAndView modelAndView = new ModelAndView();
	        modelAndView.setViewName("user_details_form");
	        String err="";
	        if(ex.hasFieldErrors("name"))
	        {
	            err+="Name Field has error\n";
	        }
	        if(ex.hasFieldErrors("email"))
	        {
	            err+="Email Field has error\n";
	        }
	        if(ex.hasFieldErrors("phone"))
	        {
	            err+="Phone Field has error\n";
	        }
	        if(ex.hasFieldErrors("state"))
	        {
	            err+="State Field has error\n";
	        }

	        modelAndView.addObject("errors",err);
	        return modelAndView;

	    }

	    @PostMapping("/addUserDetails")
	    public ModelAndView addUserDetails(@ModelAttribute @Valid FormDetailsSKill formDetailsAndSkills, @RequestParam("image") MultipartFile multipartFile, ModelAndView modelAndView, Errors result)

	    {
	        try {

	//System.out.println(result.toString());
	          if (result.hasErrors()) {
	              System.out.println(result.getErrorCount());
	               throw new Exception(result.toString());
	            }
	            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	            for (String skill : formDetailsAndSkills.getSkills()) {
	                UserSkills currSkill = skillService.addSkill(skill, formDetailsAndSkills.getEmail());
	                skillService.addSkill(currSkill);
	            }
	            modelAndView.addObject("formDetailsAndSkills", formDetailsAndSkills);
	            Files.copy(multipartFile.getInputStream(), Paths.get("/home/extramarks/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/Spring-Assign/src/main/resources/static/images/" + fileName), StandardCopyOption.REPLACE_EXISTING);
	            PhotoFile fileMaster = fileMasterService.addFileMaster(fileName, formDetailsAndSkills.getEmail());
	            fileMasterService.addFileMaster(fileMaster);
	            formDetailsAndSkills.setPhotos("http://localhost:8080/getfile/" + String.valueOf(fileMaster.getId()));
	            userDetailsService.addUserDetails(formDetailsAndSkills.getFormDetails());
	            modelAndView.addObject("image", "http://localhost:8080/getfile/" + String.valueOf(fileMaster.getId()));
	            modelAndView.setViewName("user_details_success");
	            return modelAndView;
	        }
	        catch(Throwable e)
	        {
	            modelAndView.setViewName("user_details_form");
	            modelAndView.addObject("errors", e.toString());
	            return modelAndView;
	        }
	    }

	    @PostMapping("/updateUser")
	    public  ModelAndView updateUser(@RequestParam("email") String email,ModelAndView modelAndView)
	    {
	        System.out.println(email);
	        modelAndView.setViewName("update_user_details_form");
	        modelAndView.addObject("userDetails",userDetailsService.getUserDetailsByEmail(email));
	        modelAndView.addObject("userSkills",skillService.getAllByFormDetailsId(email));
	        modelAndView.addObject("image",userDetailsService.getUserDetailsByEmail(email).getPhotos());
	        System.out.println(skillService.getAllNameByFormDetailsId(email));
	        modelAndView.addObject("allSkills",skillService.getAllNameByFormDetailsId(email));
	        return modelAndView;
	    }

	    @PostMapping("/updateEditedUserDetails")
	    public ModelAndView updateEditedUserDetails( @ModelAttribute @Valid FormDetailsSKill formDetailsAndSkills
	            , @RequestParam("oldEmail") String oldEmail
	            , @RequestParam("updateimage") MultipartFile multipartFile, ModelAndView modelAndView
	            , Errors result)

	    {
	        try {

	//System.out.println(result.toString());
	            if (result.hasErrors()) {
	                System.out.println(result.getErrorCount());
	                throw new Exception(result.toString());
	            }
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        System.out.println(formDetailsAndSkills+" "+oldEmail+fileName);
	        for(String skill:formDetailsAndSkills.getSkills())
	        {
	            UserSkills currSkill=skillService.addSkill(skill,formDetailsAndSkills.getEmail());
	            skillService.updateSkill(oldEmail,currSkill);
	        }
	        modelAndView.setViewName("user_details_success");
	        modelAndView.addObject("formDetailsAndSkills",formDetailsAndSkills);
	        Files.copy(multipartFile.getInputStream(), Paths.get("/home/extramarks/Documents/workspace-spring-tool-suite-4-4.10.0.RELEASE/Spring-Assign/src/main/resources/static/images/"+fileName), StandardCopyOption.REPLACE_EXISTING);
	        PhotoFile fileMaster=fileMasterService.addFileMaster(fileName,formDetailsAndSkills.getEmail());
	        fileMasterService.updateFileMaster(oldEmail,fileMaster);
	        formDetailsAndSkills.setPhotos("http://localhost:8080/getfile/"+String.valueOf(fileMaster.getId()));
	        userDetailsService.updateUserDetails(oldEmail,formDetailsAndSkills.getFormDetails());
	        modelAndView.addObject("image","http://localhost:8080/getfile/"+String.valueOf(fileMaster.getId()));
	        return modelAndView;
	        }
	        catch(Throwable e)
	        {
	            modelAndView.setViewName("user_details_form");
	            modelAndView.addObject("errors", e.toString());
	            return modelAndView;
	        }
	    }
}
