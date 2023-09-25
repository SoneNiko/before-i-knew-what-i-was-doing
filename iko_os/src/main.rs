#![feature(abi_x86_interrupt)]
#![no_std]
#![no_main]


use core::panic::PanicInfo;

mod vga_buffer;
mod interrupt;


#[no_mangle]
pub extern "C" fn _start() -> ! {
    println!("Redacted{}", "!");





    loop {}
}

#[panic_handler]
fn panic(info: &PanicInfo) -> ! {
    println!("{}", info);
    loop {}
}
