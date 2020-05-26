package com.adamly.xin6.controller.advice;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/5/25 22:18
 */
//@ControllerAdvice(basePackages = "com.adamly.xin6.controller.*" ,annotations = Controller.class)
//public class MyControllerAdvice {
//    //    统一的异常处理方法
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Object handlerException(HttpServletRequest request, Exception ex){
//        Map<String, Object> responseData=new HashMap<String, Object>();
//        if(ex instanceof BusinessException){
//            BusinessException businessException=(BusinessException)ex;
//            responseData.put("errCode",businessException.getErrCode());
//            responseData.put("errMsg",businessException.getErrMsg());
//            System.out.println("hhhh3");
//        } else {
//            responseData.put("errCode", EmBusinessErrror.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg",EmBusinessErrror.UNKNOWN_ERROR.getErrMsg());
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
//        System.out.println("hhhh");
//        ModelAndView mv = new ModelAndView();
//        MappingJackson2JsonView jsonView= new MappingJackson2JsonView();
//        mv.setView(jsonView);
//        mv.addObject("return",CommonReturnType.create(responseData,"fail"));
//        return mv;
//    }
//}
