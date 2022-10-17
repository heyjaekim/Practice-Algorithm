

@Controller
public class SimpleConverterController {
    
    @RequestMapping(value="/test/simpleTest.do", method=RequestMethod.GET)
    public String simpleTestForm() {
        return "test/simpleTestForm";
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String simpleTest(@RequestBody String body){
        return body;
    }
}
