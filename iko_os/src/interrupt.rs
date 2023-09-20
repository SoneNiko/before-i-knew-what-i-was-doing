
use x86_64::structures::idt::InterruptDescriptorTable;
use crate::{println, println_err};


pub fn init_idt() {
    let mut idt = InterruptDescriptorTable::new;
    idt.breakpoint.set_handler_fn(breakpoint_handler);
}

extern "x86-interrupt" fn breakpoint_handler(stack_frame: InterruptStackFrame)
{
    println_err!("EXCEPTION: BREAKPOINT\n{:#?}", stack_frame)
}