import React from 'react';

const Spacer = ({
                    size= 10,
                    axis = "horizontal",
                    style = {}
                }) => {
    const w = axis === 'vertical' ? 1 : size;
    const h = axis === 'horizontal' ? size : 1;
    return (
        <span
            style={{
                display: 'block',
                width: w,
                minWidth: w,
                height: h,
                minHeight: h
            }}

        />
    );
};
export default Spacer;
