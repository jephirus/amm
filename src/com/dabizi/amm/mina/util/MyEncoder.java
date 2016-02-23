package com.dabizi.amm.mina.util;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * @author lt 
 * 编码器：不做任何操作，数据已是约定好的格式，按原格式编码
 */
public class MyEncoder extends ProtocolEncoderAdapter {
	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {

	}
}
